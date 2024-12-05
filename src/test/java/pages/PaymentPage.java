package pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import utils.helper.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaymentPage extends Action {

    //This method generates a random expiry date in the format MM/YY
    public String getRandomExpiryDate() {
        int month = new java.util.Random().nextInt(12) + 1;
        int year = new java.util.Random().nextInt(5) + 25; // 2025-2030
        return String.format("%02d/%02d", month, year);
    }

    //This method generates a random 3-digit CVV number.
    public String getRandomCvv() {
        return String.format("%03d", new java.util.Random().nextInt(1000));
    }

    //This method reads card numbers from a file specified by the 'filePath'.
    private List<String> readCardNumbersFromFile(String filePath) {
        List<String> cardNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cardNumbers.add(line.trim());  // Her satırdaki kart numarasını listeye ekle
            }
        } catch (IOException e) {
            e.printStackTrace();  // Dosya okuma hatası durumunda hata mesajı bas
        }
        return cardNumbers;
    }

    //This method retrieves a random card number from a file specified by the 'filePath'.
    public String getRandomCardNumber(String filePath) {
        List<String> cardNumbers = readCardNumbersFromFile(filePath);
        if (cardNumbers.isEmpty()) {
            return null;  // Eğer dosyada kart numarası yoksa null döndür
        }
        Random rand = new Random();
        return cardNumbers.get(rand.nextInt(cardNumbers.size()));  // Rastgele bir kart numarası döndür
    }

    //This method fills in the card details form with values.
    public void fillCardDetails() {
        clearAndFillInput("cardHolderNameInput", "Test User");
        clearAndFillInput("cardNumberInput", getRandomCardNumber("src/test/resources/dao/TestCards.txt"));
        clearAndFillInput("expiryDateInput", getRandomExpiryDate());
        clearAndFillInput("cvvInput", getRandomCvv());
    }

}
