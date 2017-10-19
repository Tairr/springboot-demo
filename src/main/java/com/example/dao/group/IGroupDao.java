package com.example.dao.group;


import com.example.domain.group.GroupInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
@Repository("groupDao")
public interface IGroupDao {
    @Select("<script>SELECT * FROM tbl_group</script>")
     List<GroupInfo> queryForObject();
    @Select("<script>SELECT GroupID FROM tbl_group</script>")
    List<Integer> queryForObject1();
    @Select("<script>SELECT PlatformID FROM tbl_platform</script>")
    List<Integer> queryForObject2();

@Insert("<script>INSERT INTO tbl_group (GroupID,Type,VirtualOrgID,Name,ParentID,BusinessGroupID,ParentOrgID) VALUES "+
        "(#{GroupID},#{Type},#{VirtualOrgID},#{Name},#{ParentID},#{BusinessGroupID},#{ParentOrgID})</script>")
     void addForObject(GroupInfo groupInfo);

@Update("<script>UPDATE tbl_group SET Type=#{Type},VirtualOrgID=#{VirtualOrgID},Name=#{Name},ParentID=#{ParentID},"+
        "BusinessGroupID=#{BusinessGroupID},ParentOrgID=#{ParentOrgID} WHERE GroupID=#{GroupID}</script>")
    void updateForObject(GroupInfo groupInfo);

    @Delete("<script>DELETE FROM tbl_group WHERE GroupID=#{GroupID} </script>")
    default void deletForObject(GroupInfo groupInfo) {

    }

}
