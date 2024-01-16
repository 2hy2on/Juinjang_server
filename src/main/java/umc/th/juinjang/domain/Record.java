package umc.th.juinjang.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Random;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.th.juinjang.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Record extends BaseEntity {

  @Id
  @Column(name="record_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recordId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "limjang_id")
  private Limjang limjangId;

  @Column(nullable = false)
  private String recordName;

//  private URL fileUrl;
// ============ 이거 점검
  @Lob
  @Column(nullable = false)
  private String recordUrl;

  @Column(nullable = false, columnDefinition = "text")
  private String recordScipt;

  //녹음 길이
  @Column(nullable = false)
  private Long recordTime;

}
