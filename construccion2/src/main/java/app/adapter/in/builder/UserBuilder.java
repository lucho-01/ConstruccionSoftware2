/*package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.UserValidator;
import app.domain.model.User;

@Component
public class UserBuilder {
	
	@Autowired
	private UserValidator userValidator;
	
	
	public User build(String fullName, String document, String userName, String password) throws Exception {
		User user = new User();
		user.setFullName(userValidator.fullNameValidator(fullName));
		user.setDocument(userValidator.documentValidator(document));
		user.setUserName(userValidator.userNameValidator(userName));
		user.setPassword(userValidator.passwordValidator(password));
		return user;
	}

}
*/