package com.expert.project.noty.service.ai;

import com.expert.project.noty.dto.ai.currentserver.STTResponse;
import com.expert.project.noty.entity.AudioFileEntity;
import com.expert.project.noty.entity.SummationEntity;
import com.expert.project.noty.exception.ResourceNotFoundException;
import com.expert.project.noty.repository.AudioFileRepository;
import com.expert.project.noty.repository.SummationRepository;
import org.springframework.stereotype.Service;

@Service
public class STTService {

    private final AudioFileRepository audioFileRepository;
    private final SummationRepository summationRepository;

    public STTService(AudioFileRepository audioFileRepository,
                      SummationRepository summationRepository) {
        this.audioFileRepository = audioFileRepository;
        this.summationRepository = summationRepository;
    }

    public STTResponse getSTTBySavedFileName(String savedFileName, String userId) {

        System.out.println(savedFileName);
        // savedFileName으로 audio 파일 찾기
        AudioFileEntity audioFileEntity = audioFileRepository.findBySavedName(savedFileName)
                .orElseThrow(() -> new ResourceNotFoundException("Audio file not found"));

        // 해당 사용자와 오디오 ID에 맞는 summation 조회
        SummationEntity summationEntity = summationRepository.findByUserIdAndAudioId(
                        userId, audioFileEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Summation not found"));

        String result;

        if (summationEntity.getStt() == null) {
            result = "null";
        } else {
            result = summationEntity.getStt();
        }

        return new STTResponse(result);
    }

    public String getSTTByAudioId(Long audioId) {
        AudioFileEntity audioFileEntity = audioFileRepository.findById(audioId)
                .orElseThrow(() -> new ResourceNotFoundException("Audio file not found"));

        SummationEntity summationEntity = summationRepository.findByAudioId(
                        audioFileEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Summation not found"));

        String result;

        if (summationEntity.getStt() == null) {
            result = "null";
        } else {
            result = summationEntity.getStt();
        }

        return result;
    }
}
