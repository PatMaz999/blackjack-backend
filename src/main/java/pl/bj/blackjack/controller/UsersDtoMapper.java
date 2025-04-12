package pl.bj.blackjack.controller;

import pl.bj.blackjack.controller.dto.UsersDto;
import pl.bj.blackjack.model.Users;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDtoMapper {
    private UsersDtoMapper() {}

    public static List<UsersDto> mapToUsersDtos(List<Users> users) {
        return users.stream()
                .map(UsersDtoMapper::mapToUsersDto)
                .collect(Collectors.toList());
    }

    private static UsersDto mapToUsersDto(Users user) {
        return UsersDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .points(user.getPoints())
                .gameInProgress(user.isGameInProgress())
                .currentGameId(user.getCurrentGameId())
                .build();
    }
}
