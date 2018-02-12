package com.example.pawel.orlikapp.ui.create_player;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.utils.Validation;

/**
 * Created by Pawel on 12.02.2018.
 */

public class CreatePlayerInteractor {

    public interface PlayerValidator {
        void emptyFirstName();

        void emptyLastName();

        void emptyDate();

        void incrrectDate();
    }

    public static boolean onPlayerValidate(Player player, PlayerValidator playerValidator) {
        boolean flag = true;
        if(!Validation.validate(player.getBirthDate(),Validation.VALID_BIRTH_DATE_REGEX)){
            playerValidator.incrrectDate();
            flag = false;
        }
        if (player.getFirstName().equals("")) {
            playerValidator.emptyFirstName();
            flag = false;
        }

        if (player.getLastName().equals("")){
            playerValidator.emptyLastName();
            flag = false;
        }
        if (player.getBirthDate().equals("")){
            playerValidator.emptyDate();
            flag = false;
        }
        return flag;
    }
}
