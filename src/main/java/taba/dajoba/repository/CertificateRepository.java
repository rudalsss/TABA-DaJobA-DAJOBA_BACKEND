package taba.dajoba.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.Certificate;
import taba.dajoba.domain.UserSkill;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificateRepository {

    private final EntityManager em;

    //모든 자격정보 조회
    public List<Certificate> findAll(){
        return em.createQuery("select c from Certificate c", Certificate.class)
                .getResultList();
    }

    //이름으로 자격정보 조회
    public List<Certificate> findByName(String skillName){
        return em.createQuery("select c from Certificate c where c.skillName = :name", Certificate.class)
                .setParameter("name", skillName)
                .getResultList();
    }

    //테스트용 :: 자격증 정보 저장 -> DB에서 수행
    public Certificate saveCertificate(Certificate certificate){
        em.persist(certificate);
        return certificate;
    }
}
