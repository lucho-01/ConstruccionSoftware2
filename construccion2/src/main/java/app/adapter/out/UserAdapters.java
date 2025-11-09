/*package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.User;
import app.domain.port.UserPort;
import app.infrastructure.entities.UserEntity;
import app.infrastructure.mapper.UserMapper;
import app.infrastructure.repository.UserRepository;

@Service
public class UserAdapters implements UserPort{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByDocument(User user) throws Exception {
		UserEntity userEntity = userRepository.findByDocument(user.getDocument());
		return userMapper.toDomain(userEntity);
	}
	
	@Override
	public User findByUserName(User user) throws Exception {
		UserEntity userEntity = userRepository.findByUserName(user.getUserName());
		return userMapper.toDomain(userEntity);
	}

	@Override
	public void save(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

*/
