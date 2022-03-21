package utilities;



import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

        //kendisine gelen datayi Java objesine cevirir
        //(String json,Class<T> cls)
       /**T yapisi:data type belli olmayan yapilarda kullanilir,T herhangi bir Data Type demek
        * list olur,map gelebilir,cok jenerik bir yapidir T,object kapsar,datanin durumuna gore kendisi karar verip
        * bir sonuc verir*/




        private static ObjectMapper mapper;

        static{
            mapper=new ObjectMapper();  //ilk calisan kisim burasidir
        }
        public static <T> T convertJsonToJava(String json,Class<T> cls){
            T javaResult= null;
            try {
                javaResult = mapper.readValue(json, cls);
            } catch (IOException e) {
                System.err.println("json datası javaya dönüştürülemedi");  //eror mesajlari icin err,kirmizi gorunur ekranda
            }
            return javaResult;
        }
    }




    /**!!!!!!!!!!!önemli fark
    hatayi throws yada try catch yapmak farki
    resuable methodlarinda genelde try catch kullanilir cunku baska yerdeb bu classi kullanmam
    gerektiginde bir daha handle etmemize gerek kalmaz hata vermez
    ama try catch yerine throw yapsadik baska class tan cagirilinca hata verir ve tekrar
    throw yapmak zorunda kalirim
    */








