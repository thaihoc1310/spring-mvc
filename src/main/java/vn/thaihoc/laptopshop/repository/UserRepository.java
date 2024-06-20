package vn.thaihoc.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.thaihoc.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User thaihoc);

    List<User> findAllByEmail(String email);

    User findOneByEmail(String email);

    // thuc te one voi all khong co tac dung , kieu du lieu tra ve moi co tac dung
}
