package com.ferramentas.ferramentasbackend.mappers;

import com.ferramentas.ferramentasbackend.entities.Review;
import com.ferramentas.ferramentasbackend.dto.ReviewPresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "pkReview", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "publishedTime", target = "publishedTime")
    @Mapping(source = "fkNormalUser", target = "user")
    ReviewPresentationDto ReviewtoReviewPresentationDto(Review review);
}
