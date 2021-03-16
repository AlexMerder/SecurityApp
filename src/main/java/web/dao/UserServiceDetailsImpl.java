package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserServiceDetailsImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.loadUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("user not found!");
        }
        return user;
    }
}
