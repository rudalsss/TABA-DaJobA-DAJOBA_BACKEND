package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserForm;
import taba.dajoba.domain.User;
import taba.dajoba.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
  
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(UserForm userForm) {
        //중복회원검사
        validateDuplicateMember(userForm);
        //dto->entity변환
        User userEntity = User.toUserEntity(userForm);
        //저장작업
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    private void validateDuplicateMember(UserForm userForm) {
        List<User> findUsers = userRepository.findByUserId(userForm.getUserId());
        if( !findUsers.isEmpty() ){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public UserForm login(UserForm userForm) {
        List<User> findUsers = userRepository.findByUserId(userForm.getUserId());

        if (!findUsers.isEmpty()) {
            User user = findUsers.get(0);
            if (user.getPassword().equals(userForm.getPassword())) {
                // 비밀번호 일치
                return User.toUserForm(user);
            } else {
                // 비밀번호 불일치
                log.info("비밀번호 불일치");
                return null;
            }
        } else {
            // 아이디 존재X
            log.info("존재하지 않는 아이디");
            return null;
        }
    }
}