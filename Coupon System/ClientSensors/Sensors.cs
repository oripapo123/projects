using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServerBusinessLogic
{
    public class Sensors : ISensors
    {
        public String getAdressFromGPS()
        {
            GPS gps = new GPS();
            string address = "";
            if(gps.hasAcess()){
                if(gps.isActive()){
                    address=gps.getLocation();
                }
                else {
                    throw new Exception ("Enable the GPS on your device to use the GPS features");
                }
            }
            else {
                throw new Exception ("Cannot access the GPS on this device. Please check your device's settings");
            }

            return gps.hasAcess().ToString();
        }
    }
}
