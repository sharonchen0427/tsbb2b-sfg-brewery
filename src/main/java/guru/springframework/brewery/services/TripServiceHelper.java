package guru.springframework.brewery.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.brewery.domain.TripStatesList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class TripServiceHelper {
    private static final ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();
    public TripStatesList decodeAndDecompose(String dataEncoded) {
        byte[] decoded = Base64.getMimeDecoder().decode(dataEncoded);

        byte[] decodedHeaderRemoved = Arrays.copyOfRange(decoded, 4, decoded.length);

        byte[] decompressedInput = null;
        TripStatesList list = new TripStatesList();
        try {
            decompressedInput = decompressZlib(decodedHeaderRemoved);
            String out = new String(decompressedInput);
            list = objectMapper.readValue(out, TripStatesList.class);
        } catch (Exception e){
            e.printStackTrace();;
            return null;
        }
        return list;
    }

    private byte[] decompressZlib(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[2048];
        while(!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    private enum ObjectMapperInstance {
        INSTANCE;

        private final ObjectMapper mapper = new ObjectMapper();

        private ObjectMapperInstance (){

        }

        public ObjectMapper getObjectMapper(){return mapper;}
    }
}
