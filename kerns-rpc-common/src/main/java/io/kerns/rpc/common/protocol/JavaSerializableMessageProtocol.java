package io.kerns.rpc.common.protocol;

import io.kerns.rpc.common.bean.Request;
import io.kerns.rpc.common.bean.Response;

import java.io.*;

/**
 * @author xiaohei
 * @create 2020-07-03 下午8:21
 **/
public class JavaSerializableMessageProtocol implements MessageProtocol {

    public byte[] encodeRequest(Request request) {
        return encode(request);
    }

    public Request decodeRequest(byte[] request) {
        return decode(request);
    }

    public byte[] encodeResponse(Response response) {
        return encode(response);
    }

    public Response decodeResponse(byte[] response) {
        return decode(response);
    }

    private byte[] encode(Serializable obj) {
        ByteArrayOutputStream byteArrayInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayInputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayInputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("encode request 失败");
        } finally {
            closeIfAble(byteArrayInputStream);
            closeIfAble(objectOutputStream);
        }
    }

    private <T> T decode(byte[] request) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(request);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("decode Request 失败", e);
        } finally {
            closeIfAble(byteArrayInputStream);
            closeIfAble(objectInputStream);
        }
    }

    private void closeIfAble(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
