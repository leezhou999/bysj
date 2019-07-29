package bysj.zjl.entity;
/**
 * 公告
 * @author Administrator
 *
 */
public class Notice {
	
	private int noticeId;
	private String authorName;
	private String title;
	private String abs;
	private String content;
	private String isDelete;
	private String type;
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbs() {
		return abs;
	}
	public void setAbs(String abs) {
		this.abs = abs;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", authorName=" + authorName + ", title=" + title + ", abs=" + abs
				+ ", content=" + content + ", isDelete=" + isDelete + ", type=" + type + "]";
	}
	
	
}
