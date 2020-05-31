package miaosha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class B {

    @Autowired
    A a;

    class Inner{

    }

    public void init(){
        final Inner inner = new Inner();
    }

    public static void main(String[] args) {

        final B b = new B();
        final Inner inner = b.new Inner();
    }
}
