package FactoryPattern.FactoryPattern;

public abstract class Pizza {
    //所有的Pizza都有下面的三项流程,但是不同的Pizza会在Process的部分有所差别

    public void prepared(){
        System.out.println("We are preparing now ......");
    }

    public abstract void Process();

    public void over(){
        System.out.println("Done!");
    }
}
