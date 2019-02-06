package cn.java.ckEc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.java.ckEc.entity.Member;

public interface MemberMapper {
	
    //member申请，像member_register插入一条数据
    @Insert("INSERT INTO member_register SET member_name=#{memberName},password=#{password},"
    		+ "major=#{major},description=#{description},sex=IF(TRUE,1,0),"
    		+ "head_sculpture=#{headSculpture},"
    		+ "cellphone=#{cellphone},time_created=#{timeCreated},status=0,power_id=0,type_power=0")
    public int insert(Member member);
    
    //administrator申请，像administrator_register插入一条数据
    @Insert("INSERT INTO administrator_register SET admin_name=#{memberName},"
    		+ "major=#{major},description=#{description},sex=IF(TRUE,1,0),"
    		+ "password=#{password},head_sculpture=#{headSculpture},"
    		+ "status=0,time_created=#{timeCreated},cellphone=#{cellphone}")
    public int insert2(Member member);
    
    public int deleteByPrimaryKey(Byte id);
    
    public int insertSelective(Member member);

    public Member selectByPrimaryKey(Byte id);

    public int updateByPrimaryKeySelective(Member member);

    public int updateByPrimaryKey(Member member);
    
    @Select("SELECT member_name As memberName,cellphone FROM member_register WHERE member_name=#{arg0} OR cellphone=#{arg1}")
    public Member[] selectUser(String memberName,String cellphone);
    
    @Select("SELECT admin_name AS memberName,cellphone FROM administrator_register WHERE admin_name=#{arg0} OR cellphone=#{arg0}")
    public Member[] selectAdministrator(String memberName,String cellphone);
    
    //从member_register根据member_name查找一条member_register封装到member
	@Select("SELECT member_name AS memberName,password,description,major,sex,head_sculpture,cellphone FROM member_register WHERE member_name=#{arg0}")
	public Member selectUserByName(String memberName);
	
	//从administrator_register根据admin_name查找一条administrator_register封装到member
	@Select("SELECT admin_name AS memberName,password FROM administrator_register WHERE admin_name = #{arg0}")
	public Member selectUserByName2(String adminName);
	
	//从member根据member_name查找一条member封装到member
	@Select("SELECT member_name AS memberName,password FROM member WHERE member_name = #{arg0}")
	public Member selectUserByName3(String memberName);
	
	//从administrator根据admin_name查找一条administrator封装到member 
	@Select("SELECT id,admin_name AS memberName,password,major,description ,head_sculpture AS headSculpture FROM administrator WHERE admin_name = #{arg0}")
	public Member selectUserByName4(String adminName);
	
	//从super_administrator根据admin_name查找一条super_administrator封装到member
	@Select("SELECT admin_name AS memberName,password FROM super_administrator WHERE admin_name = #{arg0}")
	public Member selectUserByName5(String adminName);
	
	//从member根据member_name查找一条member
	@Select("SELECT id,member_name AS memberName,password,major,description,head_sculpture AS headSculpture FROM member WHERE member_name = #{arg0}")
	public Member selectUserByName6(String memberName);
	
	//从super_administrator根据admin_name查找一条super_administrator封装到member
	@Select("SELECT id,admin_name AS memberName,password, head_sculpture AS headSculpture FROM super_administrator WHERE admin_name = #{arg0}")
	public Member selectUserByName7(String adminName);
}