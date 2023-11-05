package utils;

import com.globallogic.clients.dtos.ClientDTO;

public class ClientTestUtil {
    public static ClientDTO getClient() {
        return ClientDTO.builder().id(7).fullName("John Doe").address("Av. Corrientes 485").dni(10100100L).build();
    }

    public static ClientDTO getInvalidClient() {
        return ClientDTO.builder().id(893224).dni(28283939494L).build();
    }
}
