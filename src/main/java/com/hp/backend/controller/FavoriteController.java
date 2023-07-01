package com.hp.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.model.favorite.dto.FavoriteListMenteeResponseDTO;
import com.hp.backend.service.Account.AccountService;
import com.hp.backend.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class FavoriteController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;

    @GetMapping("/mentee/favorite")
    public List<FavoriteListMenteeResponseDTO> getListFavorite(HttpServletRequest request) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);

        return accountService.getListFavorite(tokenPayload.getAccount_id());
    }

    @PostMapping("/mentee/favorite/{id}")
    public void addFavortie(HttpServletRequest request,@PathVariable int id) throws CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);

        accountService.addFavorite(tokenPayload.getAccount_id(),id);
    }

    @DeleteMapping("/mentee/favorite/{id}")
    public void deleteFavorite(HttpServletRequest request,@PathVariable int id) throws CustomInternalServerException, CustomBadRequestException{
        String token = jwtTokenUtil.getRequestToken(request);
        TokenPayload tokenPayload = jwtTokenUtil.getTokenPayload(token);
        accountService.deleteFavorite(id,tokenPayload.getAccount_id());
    }

}
