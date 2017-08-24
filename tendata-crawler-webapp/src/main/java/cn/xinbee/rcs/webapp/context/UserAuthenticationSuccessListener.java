/*
package cn.xinbee.rcs.webapp.context;

import cn.xinbee.rcs.data.domain.User;
import cn.xinbee.rcs.data.repository.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.transaction.annotation.Transactional;

import cn.tendata.cas.client.security.core.userdetails.LoginUser;

@Transactional
public class UserAuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    private UserRepository userRepository;
    
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if(event.getAuthentication().getPrincipal() instanceof LoginUser){
            LoginUser loginUser = (LoginUser)event.getAuthentication().getPrincipal();
            if (loginUser.getUserId() > 0) {
                User user = userRepository.findOne(loginUser.getUserId());
                if (user == null) {
                    user = new User(loginUser.getUserId(), loginUser.getUsername());
                    if (loginUser.getParentUserId() > 0) {
                        User parent = userRepository.findOne(loginUser.getParentUserId());
                        if (parent == null) {
                            parent = new User(loginUser.getParentUserId(), loginUser.getParentUsername());
                            userRepository.save(parent);
                        }
                    }
                }
                if(!user.getUsername().equals(loginUser.getUsername())){
                    user.setUsername(loginUser.getUsername());
                }
                user.setLoginCount(user.getLoginCount() + 1);
                user.setLastLoginAt(DateTime.now());
                userRepository.save(user);
            }
        }
    }
}
*/
