package com.re_teraction.backend.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.re_teraction.backend.domain.user.vo.LoginId;
import java.io.IOException;

public class LoginIdSerializer extends StdSerializer<LoginId> {

    public LoginIdSerializer() {
        super(LoginId.class);
    }

    @Override
    public void serialize(LoginId value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeString(value.getValue());
    }
}