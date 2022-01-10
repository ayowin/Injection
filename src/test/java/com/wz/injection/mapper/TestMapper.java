package com.wz.injection.mapper;

import com.wz.injection.Inject;

@Inject
public class TestMapper {
    private class Test{
        private int id;
        private String result;

        public Test(int id,String result){
            this.id = id;
            this.result = result;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", result='" + result + '\'' +
                    '}';
        }
    }

    public String select(){
        Test test = new Test(1,"select sucess");
        return test.toString();
    }
}
