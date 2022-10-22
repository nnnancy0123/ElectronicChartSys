package jp.dcnet.ecs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ecs.mapper.EcsMapper;

@Service
public class LoginService {
	@Autowired
	EcsMapper ecsMapper;

	public boolean findUser(String ishiId, String password) {

		try {
			int res = ecsMapper.findUser(ishiId, password);
			System.out.println(res);
			if (res == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			return false;

		}

	}

}
