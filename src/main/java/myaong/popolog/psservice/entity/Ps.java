package myaong.popolog.psservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`ps`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ps extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ps_id")
	private Long id;

	@Column(name = "member_id", nullable = false, updatable = false)
	private Long memberId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "position", nullable = false)
	private String position;

	@Lob
	@Column(name = "reason", nullable = false)
	private String reason;

	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@Builder
	public Ps(Long memberId, String title, String position, String reason, String content) {
		this.memberId = memberId;
		this.title = title;
		this.position = position;
		this.reason = reason;
		this.content = content;
	}

	public void update(String title, String position, String reason, String content) {
		this.title = title;
		this.position = position;
		this.reason = reason;
		this.content = content;
	}
}
