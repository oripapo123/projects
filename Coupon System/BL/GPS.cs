using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Device.Location;

namespace ServerBL
{
    class GPS : IGPS
    {

        //returns a double array of {longitude, lattitude, GeoPositionAccuracy}
        public double[] getGPSLocation()
        {
            GeoCoordinate gc=new GeoCoordinate();
            double[] ans=new double[3];
            ans[0]=gc.Longitude;
            ans[1]=gc.Latitude;
            ans[2]=gc.HorizontalAccuracy;
            return ans;
        }

        public bool hasAcess()
        {
            GeoCoordinateWatcher gw = new GeoCoordinateWatcher();
            return gw.Permission==GeoPositionPermission.Granted;
        }
        public bool isActive()
        {
            GeoCoordinateWatcher gw = new GeoCoordinateWatcher();
            return gw.Status==GeoPositionStatus.Ready;
        }
        public string getLocation() 
        { 
            GeoCoordinateWatcher gw=new GeoCoordinateWatcher();
            CivicAddressResolver ca=new CivicAddressResolver();
            return ca.ResolveAddress(gw.Position.Location).City;
        }

    }
}
