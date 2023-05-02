package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.CardConvertor;
import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.Model.Card;
import com.Shopping.Ecommerce.App.Model.Customer;
import com.Shopping.Ecommerce.App.Repository.CustomerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.CardRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CardDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CardResponseDto;
import com.Shopping.Ecommerce.App.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws Exception {
        if(cardRequestDto.getCardNo().length() != 16){
            throw new Exception("Invalid Card number");
        }
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        //create the card
        Card card = CardConvertor.cardRequestDtoToCard(cardRequestDto);

        //add the card in customer card list
        customer.getCards().add(card);
        card.setCustomer(customer);

        //save the customer
        customerRepository.save(customer);

        //build the cardResponse dto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());
        List<Card> cards = customer.getCards();
        List<CardDto> cardDtos = new ArrayList<>();
        for(Card card1 : cards){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());
            cardDtos.add(cardDto);
        }
        cardResponseDto.setCards(cardDtos);
        return cardResponseDto;
    }
}
