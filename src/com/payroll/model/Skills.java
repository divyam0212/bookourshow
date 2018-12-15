package com.payroll.model;

public class Skills {
	
	private int skillId;
	private String skillName;
	
	public Skills() {
		
	}
	public Skills(int skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	@Override
	public String toString() {
		return "Skills [skillId=" + skillId + ", skillName=" + skillName + "]";
	}
}
