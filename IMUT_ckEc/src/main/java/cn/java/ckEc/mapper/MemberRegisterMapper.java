package cn.java.ckEc.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.ckEc.entity.Member;

public interface MemberRegisterMapper 
{
	@Select("SELECT member_name AS memberName,password,major,description,head_sculpture AS headSculpture,"
			+ "sex,cellphone FROM member_register WHERE member_name=#{arg0}")
	public Member selectMemberByName(String memberName);
	
	@Select("SELECT status FROM member_register WHERE member_name=#{arg0}")
	public int selectMemberRegisterByName(String memberName);
	
	@Select("SELECT status FROM member_register WHERE member_name=#{member_name}")
	public Map<String,Object> selectMemberByName3(String memberName);
	
	@Select("SELECT status FROM member_register WHERE member_name=#{member_name}")
	public int selectMemberByName4(String memberName);
	
	
	@Select("SELECT member_name FROM member WHERE member_name=#{arg0}")
	public String selectMemberByName2(String memberName);
	
	@Select("SELECT member_Name FROM member_register WHERE member_name=#{arg0}")
	public String selectMemberRegisterByName2(String memberName);
	
	@Select("SELECT member_Name FROM member WHERE member_name=#{arg0}")
	public String selectMemberRegisterByName3(String memberName);
	
	@Select("SELECT admin_name FROM administrator WHERE id=#{arg0}")
	public String selectAdministratorById(int id);
	
	@Select("SELECT admin_name FROM super_administrator WHERE id=#{arg0}")
	public String selectSuperAdministratorById(int id);
	
	@Insert("INSERT INTO member SET member_name=#{memberName},time_created=#{timeCreated},"
			+ "password=#{password},head_sculpture=#{headSculpture},"
			+ "major=#{major},description=#{description},"
			+ "sex=#{sex},cellphone=#{cellphone}")
    public int insertMember(Member member);
    
	public int insert(int id);
    public int deleteByPrimaryKey(String memberName);
    
    public int insertSelective(Member member);
    
    

    public int updateByPrimaryKeySelective(Member member);
    
    //管理员审批一条开发人员的申请
    @Update("UPDATE member_register SET status=1,power_id=#{arg1},type_power=#{arg2} WHERE member_name=#{arg0}")
    public int updateMemberRegisterStatus(String memberName,int userId,int powerType);
    
    @Update("UPDATE member_register SET status=2,power_id=#{arg1},type_power=#{arg2} WHERE member_name=#{arg0}")
    public int updateMemberRegisterStatus2(String memberName,int userId,int powerType);
    
    @Delete("DELETE FROM member WHERE member_name=#{arg0}")
    public int deleteByName(String memberName);
}
