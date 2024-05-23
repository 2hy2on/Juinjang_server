package umc.th.juinjang.converter.record;

import umc.th.juinjang.model.dto.record.RecordRequestDTO;
import umc.th.juinjang.model.dto.record.RecordResponseDTO;
import umc.th.juinjang.model.entity.Limjang;
import umc.th.juinjang.model.entity.Record;

import java.util.List;
import java.util.stream.Collectors;

public class RecordConverter {

    public static Record toEntity(RecordRequestDTO.RecordDto recordDto, String fileUrl, Limjang limjang, String recordName){
        return Record.builder()
                .recordName(recordName)
                .recordUrl(fileUrl)
                .recordScript(recordDto.getRecordScript())
                .recordTime(recordDto.getRecordTime())
                .limjangId(limjang)
                .build();
    }

    public static RecordResponseDTO.RecordDTO toDto(Record record){
        return RecordResponseDTO.RecordDTO.builder()
                .recordId(record.getRecordId())
                .recordName(record.getRecordName())
                .recordScript(record.getRecordScript())
                .recordTime(record.getRecordTime())
                .limjangId(record.getLimjangId().getLimjangId())
                .recordUrl(record.getRecordUrl())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt())
                .build();
    }

    public static List<RecordResponseDTO.RecordDTO> toDtoList(List<Record> entityList) {
        return entityList.stream()
                .map(RecordConverter::toDto)
                .collect(Collectors.toList());
    }

    public static RecordResponseDTO.RecordMemoDto toDto(List<Record> entityList, Limjang limjang){
        List<RecordResponseDTO.RecordDTO> recordMemoDto =  toDtoList(entityList);

        return RecordResponseDTO.RecordMemoDto.builder()
                .limjangId(limjang.getLimjangId())
                .memo(limjang.getMemo())
                .recordDto(recordMemoDto)
                .createdAt(limjang.getCreatedAt())
                .updatedAt(limjang.getUpdatedAt())
                .build();
    }

}
