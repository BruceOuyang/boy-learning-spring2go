package club.bugmakers.spring2go.clientresttemplate.bean.po;

import club.bugmakers.spring2go.clientresttemplate.bean.bo.EntryBO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class ClientUserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String accessToken;

    private Calendar accessTokenValidity;

    private String refreshToken;

    @Transient
    private List<EntryBO> entries = new ArrayList<>();
}
