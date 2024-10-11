package br.com.yuri.rentacar.dto;

import br.com.yuri.rentacar.model.CityModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDto {
        private long id;

        private String fullName;

        private String gender;

        private Date birthDay;

        private CityModel city;

}
