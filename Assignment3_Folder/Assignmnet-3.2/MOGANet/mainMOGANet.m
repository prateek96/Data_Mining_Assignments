function mainTest = mainMOGANet(fileinput,alfa,fileclassi,numclassi)

fileout=sprintf('%s.ris',fileinput);

[fid, message] = fopen(fileout,'wt');



mutationRate=0.2;
CrossoverFraction=0.8;
maxGen=50; 
PopSize=30;
numIter=1;    

if nargin  <2
    disp('not enough parameters')
    return;
else
    if nargin == 2
       numclassi=0;
       fileclassi=' '; 
       MOGANet(fileinput,alfa,fileclassi,numclassi,mutationRate,CrossoverFraction,maxGen,PopSize,numIter);
    
    else
    if nargin ==4
       MOGANet(fileinput,alfa,fileclassi,numclassi,mutationRate,CrossoverFraction,maxGen,PopSize,numIter) 

    else
        disp('wrong number of parameters')       
    end
    end
end
end
