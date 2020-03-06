public class Body{
    /** Creating 6 instance variables
     * using xxPos instead of xPos to reduce the chance of typos */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;
 
    
    /** Adding in two Body constructors that can initialize an instance of the Body class */

    /**We have given parameter names which are different than 
     * the corresponding instance variable name. If you insist on 
     * making the parameter names the same as the instance variable names 
     * for aesthetic reasons, make sure to use the "this" keyword appropriately */
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** The second constructor takes in a Body object and initialize an identical Body object */
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Calculating the distance between two Bodys */
    public double calcDistance(Body b){       
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** Calculating the force exerted on this body by the given body */
    public double calcForceExertedBy(Body b){
        double f = G * b.mass * this.mass / (this.calcDistance(b) * this.calcDistance(b));
        return f;
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        double fx = this.calcForceExertedBy(b) * dx / r;
        return fx;
    }

    public double calcForceExertedByY(Body b){
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        double fy = this.calcForceExertedBy(b) * dy / r;
        return fy;
    }

    /** calculating the net X and net Y force exerted by all bodies in that array upon the current Body */
    public double calcNetForceExertedByX(Body[] allB){
        double netFx = 0;
        for (int i = 0; i < allB.length; i ++){
            if (allB[i].equals(this)){
                continue;
            }
            else{
                netFx = netFx + this.calcForceExertedByX(allB[i]);
            }
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Body[] allB){
        double netFy = 0;
        for (Body b : allB){
            if (b.equals(this)){
                continue;
            }
            else{
                netFy = netFy + this.calcForceExertedByY(b);
            }
        }
        return netFy;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    /** Draw a body at its appropriate position using StdDraw API */
    public void draw(){
        //StdDraw.enableDoubleBuffering();
        String imgFile = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgFile);
    }


}