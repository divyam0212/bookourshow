package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.dao.SkillDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Skills;

@Service
public class SkillsService implements ISkills {

	public SkillsService() {
		// TODO Auto-generated constructor stub
	}
	SkillDAO skillDao=null;
	@Override
	public int deleteSkills(Connection connObj, Skills skills) throws PayrollException {
		// TODO Auto-generated method stub
		skillDao=new SkillDAO();
		 return skillDao.deleteSkills(connObj, skills);
	}

	@Override
	public int registerSkill(Connection connection, Skills skills) throws PayrollException {
		// TODO Auto-generated method stub
		skillDao=new SkillDAO();
		return skillDao.registerSkill(connection, skills);
	}

	@Override
	public int updateSkill(Connection connection, Skills skills) throws PayrollException {
		// TODO Auto-generated method stub
		skillDao=new SkillDAO();
		return skillDao.updateSkill(connection, skills);
	}

	@Override
	public List<Skills> fetchAllSkill() throws PayrollException {
		// TODO Auto-generated method stub
		skillDao=new SkillDAO();
		return skillDao.fetchAllSkill();
	}

}
