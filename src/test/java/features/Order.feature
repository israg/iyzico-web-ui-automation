Feature: Reviews

  Background:
    Given Check page url contains "https://www.iyzico.com/demo/"

  @checkout
  Scenario:
    And Wait for given seconds 5
    And Get list for "productList" click 2 item
    And Click "addBasketButton"
    And Click "showBasketButton"
    And Click "goToPaymentPageButton"
    And Wait for given seconds 5
    And Scroll to "DebitOrCreditCard" element
    And Click "DebitOrCreditCard"
    And Click "PayDebitOrCreditCard"
    And Fill card infos
    And Click "payButton"
    And If OTP is available fill OTP value
    And Wait for given seconds 5
    Then Check equality of "siparisAlindi" field with "Sipariş alındı"
