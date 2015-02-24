class BSearch {

    /*@ requires 
      @ ((x == -2147472218) && (tab[0]==-2147483610) && (tab[1]==-2147475891) && (tab[2]==-2147474655) && (tab[3]==-2147472218) && (tab[4]==-2147463363) && (tab[5]==-2147461567) && (tab[6]==-2147453202) && (tab[7]==-2147450920) && (tab[8]==-2147483610) && (tab[9]==-2147475929) && (tab[10]==-2147482412) && (tab[11]==-2147481211) && (tab[12]==-2147474793) && (tab[13]==-2147481852) && (tab[14]==-2147475283) && (tab[15]==-2147481366) && (tab[16]==-2147483199) && (tab[17]==-2147483039) && (tab[18]==-2147477795) && (tab[19]==-2147475550) && (tab[20]==-2147482506) && (tab[21]==-2147483367) && (tab[22]==-2147483113) && (tab[23]==-2147477728) && (tab[24]==-2147474703) && (tab[25]==-2147477365) && (tab[26]==-2147480651) && (tab[27]==-2147478969) && (tab[28]==-2147482674) && (tab[29]==-2147481760) && (tab[30]==-2147481995) && (tab[31]==-2147477744) && (tab[32]==-2147475192) && (tab[33]==-2147482325) && (tab[34]==-2147474769) && (tab[35]==-2147481408) && (tab[36]==-2147473923) && (tab[37]==-2147481373) && (tab[38]==-2147481202) && (tab[39]==-2147483058) && (tab[40]==-2147482808) && (tab[41]==-2147475062) && (tab[42]==-2147476742) &&(tab[43]==-2147482413) && (tab[44]==-2147480039) && (tab[45]==-2147481032) && (tab[46]==-2147481193) && (tab[47]==-2147482781) && (tab[48]==-2147474117) && (tab[49]==-2147476770) && (tab[50]==-2147475427) && (tab[51]==-2147475762) && (tab[52]==-2147482054) && (tab[53]==-2147483066) && (tab[54]==-2147481437) && (tab[55]==-2147482540) && (tab[56]==-2147476070) && (tab[57]==-2147476583) && (tab[58]==-2147476019) && (tab[59]==-2147474246) && (tab[60]==-2147481370) &&(tab[61]==-2147480144) && (tab[62]==-2147479262) && (tab[63]==-2147472218) && (tab[64]==-2147481320) && (tab[65]==-2147476472) && (tab[66]==-2147481317) && (tab[67]==-2147474385) && (tab[68]==-2147481536) && (tab[69]==-2147479513) && (tab[70]==-2147476722) && (tab[71]==-2147482546) && (tab[72]==-2147481998) && (tab[73]==-2147475244) && (tab[74]==-2147479313) && (tab[75]==-2147475794) && (tab[76]==-2147478050) && (tab[77]==-2147478878) && (tab[78]==-2147479552) && (tab[79]==-2147480436) &&(tab[80]==-2147478965) && (tab[81]==-2147476946) && (tab[82]==-2147478389) && (tab[83]==-2147477707) && (tab[84]==-2147480901) && (tab[85]==-2147476275) && (tab[86]==-2147474779) && (tab[87]==-2147475645) && (tab[88]==-2147476976) && (tab[89]==-2147480496) && (tab[90]==-2147481830) && (tab[91]==-2147480146) && (tab[92]==-2147483409) && (tab[93]==-2147479462) && (tab[94]==-2147480844) && (tab[95]==-2147474713) && (tab[96]==-2147480625) && (tab[97]==-2147483314) && (tab[98]==-2147483117) && (tab[99]==-2147482257) && (tab[100]==-2147477629) && (tab[101]==-2147482075) && (tab[102]==-2147477667) && (tab[103]==-2147479688) && (tab[104]==-2147483024) && (tab[105]==-2147476583) && (tab[106]==-2147476081) && (tab[107]==-2147480819) && (tab[108]==-2147479483) && (tab[109]==-2147481413) && (tab[110]==-2147482402) && (tab[111]==-2147480014) && (tab[112]==-2147478030) && (tab[113]==-2147477923) && (tab[114]==-2147480949) && (tab[115]==-2147479327) && (tab[116]==-2147478727) && (tab[117]==-2147480876) &&(tab[118]==-2147476837) && (tab[119]==-2147481587) &&(tab[120]==-2147480083) && (tab[121]==-2147478459) && (tab[122]==-2147483529) && (tab[123]==-2147474902) && (tab[124]==-2147477600) && (tab[125]==-2147474029) && (tab[126]==-2147477315) && (tab[127]==-2147477680));
      @ ensures
      @ ((\result == 3) || (\result == 63) && (tab[0]>=tab[1] && tab[1]>=tab[2] && tab[2]>=tab[3] && tab[3]>=tab[4] && tab[4]>=tab[5] && tab[5]>=tab[6] && tab[6]>=tab[7] && tab[7]>=tab[8] && tab[8]>=tab[9] && tab[10]>=tab[11] && tab[11]>=tab[12] && tab[12]>=tab[13] && tab[13]>=tab[14] && tab[14]>=tab[15] && tab[15]>=tab[16] && tab[16]>=tab[17] && tab[17]>=tab[18] && tab[18]>=tab[19] && tab[20]>=tab[21] && tab[21]>=tab[22] && tab[22]>=tab[23] && tab[23]>=tab[24] && tab[24]>=tab[25] && tab[25]>=tab[26] && tab[26]>=tab[27] && tab[27]>=tab[28] && tab[28]>=tab[29] && tab[30]>=tab[31] && tab[31]>=tab[32] && tab[32]>=tab[33] && tab[33]>=tab[34] && tab[34]>=tab[35] && tab[35]>=tab[36] && tab[36]>=tab[37] && tab[37]>=tab[38] && tab[38]>=tab[39] && tab[40]>=tab[41] && tab[41]>=tab[42] && tab[42]>=tab[43] && tab[43]>=tab[44] && tab[44]>=tab[45] && tab[45]>=tab[46] && tab[46]>=tab[47] && tab[47]>=tab[48] && tab[48]>=tab[49] && tab[50]>=tab[51] && tab[51]>=tab[52] && tab[52]>=tab[53] && tab[53]>=tab[54] && tab[54]>=tab[55] && tab[55]>=tab[56] && tab[56]>=tab[57] && tab[57]>=tab[58] && tab[58]>=tab[59] && tab[60]>=tab[61] && tab[61]>=tab[62] && tab[62]>=tab[63] && tab[63]>=tab[64] && tab[64]>=tab[65] && tab[65]>=tab[66] && tab[66]>=tab[67] && tab[67]>=tab[68] && tab[68]>=tab[69] && tab[70]>=tab[71] && tab[71]>=tab[72] && tab[72]>=tab[73] && tab[73]>=tab[74] && tab[74]>=tab[75] && tab[75]>=tab[76] && tab[76]>=tab[77] && tab[77]>=tab[78] && tab[78]>=tab[79] && tab[80]>=tab[81] && tab[81]>=tab[82] && tab[82]>=tab[83] && tab[83]>=tab[84] && tab[84]>=tab[85] && tab[85]>=tab[86] && tab[86]>=tab[87] && tab[87]>=tab[88] && tab[88]>=tab[89] && tab[90]>=tab[91] && tab[91]>=tab[92] && tab[92]>=tab[93] && tab[93]>=tab[94] && tab[94]>=tab[95] && tab[95]>=tab[96] && tab[96]>=tab[97] && tab[97]>=tab[98] && tab[98]>=tab[99] && tab[100]>=tab[101] && tab[101]>=tab[102] && tab[102]>=tab[103] && tab[103]>=tab[104] && tab[104]>=tab[105] && tab[105]>=tab[106] && tab[106]>=tab[107] && tab[107]>=tab[108] && tab[108]>=tab[109] && tab[110]>=tab[111] && tab[111]>=tab[112] && tab[112]>=tab[113] && tab[113]>=tab[114] && tab[114]>=tab[115] && tab[115]>=tab[116] && tab[116]>=tab[117] && tab[117]>=tab[118] && tab[118]>=tab[119] && tab[120]>=tab[121] && tab[121]>=tab[122] && tab[122]>=tab[123] && tab[123]>=tab[124] && tab[124]>=tab[125] && tab[125]>=tab[126] && tab[126]>=tab[127]));
      @*/
    int caller(int[] tab, int x) {
	int result = -1;
	int milieu = 0;
	int gauche = 0;
	int droite = tab.length - 1;
	while ((result == -1) && (gauche <= droite)) {
	    milieu = (gauche + droite) / 2;
	    if (tab[milieu] == x) {
		result = gauche; /*error: the instruction should be result = milieu; */ 
	    }
	    else if (tab[milieu] > x) {
		droite = milieu - 1;
	    }
	    else {
		gauche = milieu + 1;
	    }
	}
	return result;
    }

}
