package com.example.notiapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity"

    // UI 요소 변수 선언
    private lateinit var userIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var findPasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        // UI 요소 초기화
        userIdEditText = findViewById(R.id.signInUserId)
        passwordEditText = findViewById(R.id.signInPassword)
        signInButton = findViewById(R.id.signInBtn)
        signUpButton = findViewById(R.id.signUpBtn)
        findPasswordButton = findViewById(R.id.findPasswordBtn)

        // 회원가입에서 넘어온 userId가 있으면 자동 입력
        val userIdFromSignUp = intent.getStringExtra("userId")
        if (!userIdFromSignUp.isNullOrEmpty()) {
            userIdEditText.setText(userIdFromSignUp)
        }

        // 회원가입 버튼 클릭 리스너
        signUpButton.setOnClickListener {
            // 회원가입 페이지로 이동
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼 클릭 리스너
        signInButton.setOnClickListener {
            validateAndSignIn()
        }

        // 비밀번호 찾기 버튼 클릭 리스너
        findPasswordButton.setOnClickListener {
            // 비밀번호 찾기 기능은 아직 구현하지 않음
            Toast.makeText(this, "비밀번호 찾기 기능은 추후 구현 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SignInmain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validateAndSignIn() {
        val userId = userIdEditText.text.toString().trim()
        val password = passwordEditText.text.toString()

        Log.d(TAG, "로그인 시도: userId=$userId")

        // 디자인 테스트 모드: 검증 없이 바로 대시보드로 이동
        Toast.makeText(this, "디자인 테스트 모드: 로그인 성공!", Toast.LENGTH_SHORT).show()

        // 더미 토큰 생성 및 저장 (테스트용)
        val dummyToken = "design_test_dummy_token_${System.currentTimeMillis()}"
        val sharedPreferences = getSharedPreferences("auth_prefs", MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("jwt_token", dummyToken)
            .apply()

        // 바로 대시보드 화면으로 이동
        val intent = Intent(this, DashBoardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun signIn(userId: String, password: String) {
        // 로딩 표시 등 UI 업데이트
        signInButton.isEnabled = false
        Toast.makeText(this, "로그인 중...", Toast.LENGTH_SHORT).show()

        // 백그라운드 스레드에서 네트워크 요청 수행
        thread {
            try {
                // JSON 데이터 생성 - 서버 API 스펙에 맞게 필드명 설정
                val jsonObject = JSONObject().apply {
                    put("userId", userId)
                    put("password", password)
                }
                val jsonData = jsonObject.toString()
                Log.d(TAG, "서버 요청 데이터: $jsonData")

                // OkHttp 클라이언트 생성
                val client = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                // JSON으로 요청을 보내는 경우
                val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
                val requestBody = jsonData.toRequestBody(mediaType)
                val request = Request.Builder()
                    .url("http://10.0.2.2:8080/auth/login")
                    .post(requestBody)
                    .header("Content-Type", "application/json") // JSON 형식으로 일치
                    .build()

                // 요청 실행
                client.newCall(request).execute().use { response ->
                    val responseBody = response.body?.string() ?: ""
                    Log.d(TAG, "서버 응답 코드: ${response.code}")
                    Log.d(TAG, "서버 응답 본문: '$responseBody'") // 작은따옴표로 감싸서 빈 응답도 확인 가능

                    // UI 스레드에서 결과 처리
                    runOnUiThread {
                        signInButton.isEnabled = true

                        if (response.isSuccessful) {
                            try {
                                if (responseBody.isNotEmpty()) {
                                    // JSON 응답 처리
                                    val jsonResponse = JSONObject(responseBody)

                                    // 응답에 token 필드가 있는지 확인
                                    if (jsonResponse.has("token")) {
                                        val token = jsonResponse.getString("token")

                                        // 토큰을 SharedPreferences에 저장
                                        val sharedPreferences = getSharedPreferences("auth_prefs", MODE_PRIVATE)
                                        sharedPreferences.edit()
                                            .putString("jwt_token", token)
                                            .apply()

                                        Log.d(TAG, "JWT 토큰 저장 완료")
                                    } else if (jsonResponse.has("jwt") || jsonResponse.has("accessToken")) {
                                        // 다른 가능한 토큰 필드명 시도
                                        val token = if (jsonResponse.has("jwt"))
                                            jsonResponse.getString("jwt")
                                        else
                                            jsonResponse.getString("accessToken")

                                        // 토큰을 SharedPreferences에 저장
                                        val sharedPreferences = getSharedPreferences("auth_prefs", MODE_PRIVATE)
                                        sharedPreferences.edit()
                                            .putString("jwt_token", token)
                                            .apply()

                                        Log.d(TAG, "JWT 토큰 저장 완료 (대체 필드명)")
                                    } else {
                                        // 토큰 필드를 찾을 수 없음
                                        Log.w(TAG, "응답에 토큰 필드가 없습니다: $responseBody")
                                        // 그래도 로그인은 성공했으므로 계속 진행
                                    }
                                } else {
                                    // 응답 본문이 비어있음
                                    Log.w(TAG, "서버 응답이 비어있습니다")
                                    // 토큰이 헤더에 있을 수 있으므로 헤더 확인
                                    val authHeader = response.header("Authorization")
                                    if (!authHeader.isNullOrEmpty() && authHeader.startsWith("Bearer ")) {
                                        val token = authHeader.substring(7) // "Bearer " 제거

                                        // 토큰을 SharedPreferences에 저장
                                        val sharedPreferences = getSharedPreferences("auth_prefs", MODE_PRIVATE)
                                        sharedPreferences.edit()
                                            .putString("jwt_token", token)
                                            .apply()

                                        Log.d(TAG, "JWT 토큰 저장 완료 (Authorization 헤더)")
                                    }
                                }

                                // 로그인 성공 처리 계속 진행
                                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()

                                // 로그인 성공 시 대시보드로 이동
                                val intent = Intent(this, DashBoardActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish() // 현재 화면 종료

                            } catch (e: Exception) {
                                Log.e(TAG, "응답 처리 중 오류 발생: ${e.message}", e)
                                Toast.makeText(this, "로그인 응답 처리 중 오류가 발생했습니다", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            // 서버 오류 처리 (기존 코드 유지)
                            val errorMessage = when (response.code) {
                                401 -> "아이디 또는 비밀번호가 일치하지 않습니다."
                                404 -> "서버를 찾을 수 없습니다."
                                500 -> "서버 오류가 발생했습니다. 잠시 후 다시 시도하세요."
                                else -> "로그인 실패: ${response.code}"
                            }
                            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
                }

            } catch (e: IOException) {
                Log.e(TAG, "네트워크 오류: ${e.message}", e)

                // UI 스레드에서 오류 처리
                runOnUiThread {
                    signInButton.isEnabled = true
                    Toast.makeText(
                        this,
                        "서버 연결에 실패했습니다. 인터넷 연결을 확인하세요.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "예외 발생: ${e.message}", e)

                // UI 스레드에서 오류 처리
                runOnUiThread {
                    signInButton.isEnabled = true
                    Toast.makeText(
                        this,
                        "오류가 발생했습니다: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}