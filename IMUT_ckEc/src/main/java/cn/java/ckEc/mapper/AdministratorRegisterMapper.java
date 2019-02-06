package cn.java.ckEc.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.ckEc.entity.Member;

public interface AdministratorRegisterMapper 
{
	@Select("SELECT admin_name AS memberName,password,head_sculpture AS headSculpture,password,sex"
			+ ",major,description,cellphone"
			+ " FROM administrator_register WHERE admin_name=#{arg0}")
	public Member selectMemberByName(String adminName);
	
	@Select("SELECT status FROM administrator_register WHERE admin_name=#{member_name}")
	public Map<String,Integer> selectAdministratorByName3(String memberName);
	
	@Select("SELECT admin_name FROM administrator WHERE admin_name=#{arg0}")
	public String selectAdministratorByName2(String memberName);
	
	@Select("SELECT admin_Name FROM administrator_register WHERE admin_name=#{arg0}")
	public String selectMemberRegisterByName2(String adminName);
	
	@Select("SELECT admin_name FROM administrator WHERE admin_name=#{arg0}")
	public String selectMemberRegisterByName3(String adminName);
	
	@Select("SELECT admin_name FROM super_administrator WHERE id=#{arg0}")
	public String selectSuperAdministratorById(int userId);
	
	@Select("SELECT status FROM administrator_register WHERE admin_name=#{arg0}")
	public int selectAdministratorRegisterByName(String adminName);
	
	@Insert("INSERT INTO administrator SET admin_name=#{memberName},password=#{password},"
			+ "head_sculpture=#{headSculpture},major=#{major},description=#{description},"
			+ "time_created=#{timeCreated},sex=#{sex},cellphone=#{cellphone}")
    public int insertMember(Member member);
    
	public int insert(int id);
    
    public int insertSelective(Member member);
    
    

    public int updateByPrimaryKeySelective(Member member);
    
    //管理员审批一条开发人员的申请
    @Update("UPDATE administrator_register SET status=1,power_id=#{arg1} WHERE admin_name=#{arg0}")
    public int updateAdministratorRegisterStatus(String adminName,int userId);
    
    @Update("UPDATE administrator_register SET status=2,power_id=#{arg1} WHERE admin_name=#{arg0}")
    public int updateAdministratorRegisterStatus2(String memberName,int userId);
    
    
    @Delete("DELETE FROM administrator WHERE admin_name=#{arg0}")
    public int deleteAdministratorByName(String memberName);
}
