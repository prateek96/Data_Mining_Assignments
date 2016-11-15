function CC = mainGANETLG(fileinput,alfa,fileclass,numclass)

if nargin >=2
    
edgesLG=buildLineGraph(fileinput);
disp('line graph built and stored in fileinput.lg')
edges=load(fileinput);

mutationRate=0.2;
CrossoverFraction=0.8;
maxGen=50;  
PopSize=50;

CC=ganetplus(edges,edgesLG,alfa,mutationRate,CrossoverFraction,maxGen,PopSize)

[fid, message] = fopen('ris.txt','wt');  
for i1 = 1:size(CC,2)
    listnodes=CC{i1};
    for j1= 1:size(listnodes,2)
       
            fprintf(fid,'%d ',listnodes(j1));
    end
    fprintf(fid,'\n');
end
      if nargin ==4
          
      class=load(fileclass);
      numnodes=size(class,1)
      CM=confusionMatrix(CC,class,numclass) 
      end
else
    error('not enough input parameters')
end

end




function CM= confusionMatrix(CC,classi,numclass)

CM = zeros(numclass,size(CC,2));

for k=1:size(CC,2) 
   
    listnodes=CC{k};
    for j = 1: size(listnodes,2)
    nodo = listnodes(j);
          if (nodo~=0)
              classe = classi(nodo,2);
              
              CM(classe,k)=  CM(classe,k)+1;
          end
    end
end
end