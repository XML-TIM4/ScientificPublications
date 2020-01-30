package xmlteam4.Project.services;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.UserRepository;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewerService {
    @Autowired
    private UserRepository userRepository;

    public Pair<TUser, TUser> selectReviewers(ArrayList<String> authorIds, String keywords) throws XMLDBException, JAXBException {
        String[] keywordsParsed = keywords.toLowerCase().trim().split("\\s*,\\s*");

        List<TUser> possibleReviewers = userRepository.getAllUsers()
                .getUser()
                .stream()
                .filter(user -> !authorIds.contains(user.getEmail()) && !user.isEditor())
                .sorted(Comparator.comparingInt(u -> u.getExpertiseScoreForKeywords(keywordsParsed)))
                .collect(Collectors.toList());

        return new Pair<>(possibleReviewers.get(possibleReviewers.size() - 1),
                possibleReviewers.get(possibleReviewers.size() - 2));
    }
}
