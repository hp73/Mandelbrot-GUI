    //this is a singleton mandelbrot set that shoud returnt he number that 
    //doesnt' really care wht the x 


    
    public class setCalculator {

        static double xMax;
        static double xMin;
        static double yMax;
        static double yMin;
        final static double DEFAULT_XMAX = 1.0;
        final static double DEFAULT_XMIN = -2.5;
        final static double DEFAULT_YMAX = 1.0;
        final static double DEFAULT_YMIN = -1.0;
        static int limit;
        final static int DEFUALT_LIMIT = 32;

        public setCalculator() {
            xMax = DEFAULT_XMAX;
            xMin = DEFAULT_XMIN;
            yMax = DEFAULT_YMAX;
            yMin = DEFAULT_YMIN;
            limit = DEFUALT_LIMIT;
        }

        public static void changeSet(String setName){
            if(setName.equals("Mandelbrot Set")){
                xMax = DEFAULT_XMAX;
                xMin = DEFAULT_XMIN;
                yMax = DEFAULT_YMAX;
                yMin = DEFAULT_YMIN;
            }
            else{
                xMin = -1.5;
                xMax = 1.5;
                yMin = -1.5;
                yMax = 1.5;
            }
        }

        public static void resetButton(){
            limit = DEFUALT_LIMIT;
        }

        public void setLimit(int limit){
            this.limit = limit;
            System.out.println("Set Calc " + limit);
        }
    
        public static void decreaseButton(){
            if (limit > 32){
                limit = limit / 2;
            }
            else{
                limit = 32;
            }
            System.out.println(limit);
        }
        

        public void dragZoom( double minXPercent, double minYPercent, double maxXPercent, double maxYPercent){
            double newXMax = maxXPercent * (xMax - xMin) + xMin;
            double newXMin = minXPercent * (xMax - xMin) + xMin;
            double newYMax = maxYPercent * (yMax - yMin) + yMin;
            double newYMin = minYPercent * (yMax - yMin) + yMin;

            xMax = newXMax;
            xMin = newXMin;
            yMax = newYMax;
            yMin = newYMin;
        }


        public int spillTheT( double xPercentage, double yPercentage){
            Complex z0;
            Complex z;
            int t = 0;
            double real;
            double imaginary;

            real = xPercentage * (xMax - xMin) + xMin;
            imaginary = yPercentage * (yMax - yMin) + yMin;

            z = new Complex(real, imaginary);

            if(Canvas.setName.equals("Mandelbrot Set")){
                z0 = new Complex(real, imaginary);
            }
            else{
                z0 = new Complex(-0.1,0.65);
            }            

            while( t < limit ){
                if (z.abs() > 2.0){
                    return t;
                }
    
                else{
                    z = z.times(z).plus(z0);
                    ++t;
                }
            }

            return limit;


        }




    }