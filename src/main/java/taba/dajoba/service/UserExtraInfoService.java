package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserExtraInfo;
import taba.dajoba.domain.User;
import taba.dajoba.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserExtraInfoService {

    private final UserRepository userRepository;

    /**
     * 추가정보(필터링정보) 불러오기 기능
     */
    public UserExtraInfo findInfo(String userId){
        List<User> findUsers = userRepository.findByUserId(userId);
        if( !findUsers.isEmpty() ) {
            User user = findUsers.get(0);
            UserExtraInfo extraInfo = user.loadExtraInfo();
            return extraInfo;
        } else {
            return null;
        }
    }


    /**
     * 추가정보(필터링정보) 등록 및 수정하기 기능
     */
    @Transactional
    public User updateInfo(String userId, UserExtraInfo userExtraInfo){
        List<User> findUsers = userRepository.findByUserId(userId);
        if( !findUsers.isEmpty() ){
            User user = findUsers.get(0);
            user.addExtraInfo(userExtraInfo); //영속처리
            return user;
        } else {
            return null;
        }
    }

}
