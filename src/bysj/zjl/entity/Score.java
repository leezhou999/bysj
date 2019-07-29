package bysj.zjl.entity;
/**
 * 成绩表
 * @author Administrator
 *
 */
public class Score extends User_info_vo{

		private String stu_id;
		private String score1;
		private String score2;
		private String score3;
		private String score4;
		private String remark;
		private String scoreType;
		
		
		public String getScore1() {
			return score1;
		}
		public void setScore1(String score1) {
			this.score1 = score1;
		}
		public String getScore2() {
			return score2;
		}
		public void setScore2(String score2) {
			this.score2 = score2;
		}
		public String getScore3() {
			return score3;
		}
		public void setScore3(String score3) {
			this.score3 = score3;
		}
		public String getScore4() {
			return score4;
		}
		public void setScore4(String score4) {
			this.score4 = score4;
		}
		public String getStu_id() {
			return stu_id;
		}
		public void setStu_id(String stu_id) {
			this.stu_id = stu_id;
		}
		
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getScoreType() {
			return scoreType;
		}
		public void setScoreType(String scoreType) {
			this.scoreType = scoreType;
		}
		@Override
		public String toString() {
			return "Score [stu_id=" + stu_id + ", score1=" + score1 + ", score2=" + score2 + ", score3=" + score3
					+ ", score4=" + score4 + ", remark=" + remark + ", scoreType=" + scoreType + "]";
		}
		
		
}
