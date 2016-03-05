using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServerBL
{
    interface IGPS
    {
         double[] getGPSLocation();
         bool hasAcess();
         bool isActive();
         string getLocation();
    }
}
