package prev.cxw.employ.dao;

import com.google.common.collect.Lists;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Options;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import prev.cxw.employ.model.dto.Company;
import prev.cxw.employ.model.query.CompanyQuery;

import java.util.List;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
public interface CompanyDAO {

    /**
     * 查询所有的公司
     * @return
     */
//    @Select("select * from Company " +
//            " where name like '%#{name}%'" +
//            " and phone like '%#{phone}%'" +
//            " and address like '%#{address}%'" +
//            "")
//    List<Company> search(CompanyQuery query);
//
//
//    @Select("select * from Company ")
//    List<Company> searchAll();
//
//    /**
//     * 插入
//     * @param company
//     */
//    @Insert("insert into Company(name,address,phone,detail) " +
//            " values(#{name},#{address},#{phone},#{detail})")
//    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
//    void insert(Company company);
//
//    /**
//     * 更改
//     * @param company
//     */
//    @Update("update Company " +
//            "set name=#{name},address=#{address},phone=#{phone},detail=#{detail} " +
//            "where id=#{id}")
//    void save(Company company);

    List<Company> companys = Lists.newArrayList(
            new Company(1,"A公司","文三路","2858697","简介"),
            new Company(2,"B公司","文二路","2858697","简介")
    );


    default List<Company> searchAll(){
        return companys;
    }

    default Company getById(long id){
        for(Company com : companys){
            if(com.getId() == id){
                return com;
            }
        }
        return null;
    }

    default void insert(Company company){
        companys.add(company);
    }

    default List<Company> search(CompanyQuery query){
        return companys;
    }

    default void save(Company company){
        Company company1 = getById(company.getId());
        company1.setDetail(company.getDetail());
        company1.setAddress(company.getAddress());
        company1.setPhone(company.getPhone());
        company1.setName(company.getName());
    }

}
