package jp.dcnet.ecs.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EcsMapper {

	int findUser(String ishiId, String password);
}
