package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.ReviewPresentationDto;
import com.ferramentas.ferramentasbackend.dto.SmallPresentationUserDto;
import com.ferramentas.ferramentasbackend.dto.input.NewReviewInputDto;
import com.ferramentas.ferramentasbackend.entities.Account;
import com.ferramentas.ferramentasbackend.entities.NormalUser;
import com.ferramentas.ferramentasbackend.entities.Review;
import com.ferramentas.ferramentasbackend.entities.SubService;
import com.ferramentas.ferramentasbackend.repository.*;
import com.xtragou.xtragoubackend.repository.*;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(RouterUtils.REVIEW_ENDPOINT)
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final AccountRepository accountRepository;
    private final SubServiceRepository subServiceRepository;
    private final PersonRepository personRepository;
    private final NormalUserRepository normalUserRepository;
    private final UserSubServiceClassificationRepository userSubServiceClassificationRepository;

    public ReviewController(ReviewRepository reviewRepository, AccountRepository accountRepository, SubServiceRepository subServiceRepository, PersonRepository personRepository, NormalUserRepository normalUserRepository, UserSubServiceClassificationRepository userSubServiceClassificationRepository) {
        this.reviewRepository = reviewRepository;
        this.accountRepository = accountRepository;
        this.subServiceRepository = subServiceRepository;
        this.personRepository = personRepository;
        this.normalUserRepository = normalUserRepository;
        this.userSubServiceClassificationRepository = userSubServiceClassificationRepository;
    }

    @GetMapping(value = "/sub_service", params = {"subService"})
    public ResponseEntity<List<ReviewPresentationDto>> getReviewsOfSubService(Integer subService) {
        List<Review> reviewsOfSubService = reviewRepository.findAllByFkSubService_PkSubService(subService);

        List<ReviewPresentationDto> reviews = reviewsOfSubService.stream().map(review -> {
            return new ReviewPresentationDto(
                    review.getPkReview(),
                    review.getDescription(),
                    review.getRating(),
                    review.getPublishedTime(),
                    new SmallPresentationUserDto(
                            accountRepository
                                    .findFkPerson_ProfilePicture(review.getFkNormalUser().getFkPerson().getPkPerson())
                            , review.getFkNormalUser().getFkPerson().getName())
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(reviews);
    }

    @GetMapping(value = "/technician", params = {"technician"})
    public ResponseEntity<List<ReviewPresentationDto>> getReviewsOfTechnician(Integer technician) {
        List<Review> reviewsOfSubService = reviewRepository.findAllByFkSubService_FkTechnician_PkTechnician(technician);

        List<ReviewPresentationDto> reviews = reviewsOfSubService.stream().map(review -> {
            return new ReviewPresentationDto(
                    review.getPkReview(),
                    review.getDescription(),
                    review.getRating(),
                    review.getPublishedTime(),
                    new SmallPresentationUserDto(
                            accountRepository
                                    .findFkPerson_ProfilePicture(review.getFkNormalUser().getFkPerson().getPkPerson())
                            , review.getFkNormalUser().getFkPerson().getName())
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(reviews);
    }

    @PostMapping(value = "/sub_service")
    public ResponseEntity<ReviewPresentationDto> submitServiceReview(@Valid @RequestBody NewReviewInputDto newReviewInputDto) {
        Optional<SubService> subServiceOptional = subServiceRepository.findById(newReviewInputDto.getSubServiceId());

        subServiceOptional.orElseThrow(() -> {
            throw new Error("Sub Service Error");
        });

        Optional<Account> accountOptional = accountRepository.findById(newReviewInputDto.getPublisherId());

        accountOptional.orElseThrow(() -> {
            throw new Error("Account Error");
        });




        Optional<NormalUser> normalUserOptional = normalUserRepository.findByFkPerson_PkPerson(accountOptional.get().getFkPerson().getPkPerson());

        normalUserOptional.orElseThrow(() -> {
            throw new Error("Normal User Error");
        });

        Review review = new Review();

        review.setDescription(newReviewInputDto.getDescription());
        review.setFkSubService(subServiceOptional.get());
        review.setFkNormalUser(normalUserOptional.get());
        review.setPublishedTime(new Date(System.currentTimeMillis()));
        review.setRating(newReviewInputDto.getRating());

        reviewRepository.save(review);

        ReviewPresentationDto reviewPresentationDto = new ReviewPresentationDto(
                review.getPkReview(),
                review.getDescription(),
                review.getRating(),
                review.getPublishedTime(),
                new SmallPresentationUserDto(
                        accountRepository
                                .findFkPerson_ProfilePicture(review.getFkNormalUser().getFkPerson().getPkPerson())
                        , review.getFkNormalUser().getFkPerson().getName())
        );

        return ResponseEntity.ok().body(reviewPresentationDto);
    }
}
