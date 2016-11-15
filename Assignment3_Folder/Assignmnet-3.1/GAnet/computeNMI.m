function NMI= computeNMI(CM,n)

NMI = 0.0;
numeratore = 0;

for i=1:size(CM,1) 
    
    Ni = sum(CM(i,:)); 
  
    for j = 1: size(CM,2)
       
       if (CM(i,j) ~=0)
         Nj = sum(CM(:,j)); 
         numeratore = numeratore + CM(i,j) * log((CM(i,j)*n)/(Ni*Nj));
       end
    end
end

denom1=0;
for i=1:size(CM,1) 
    
    Ni = sum(CM(i,:)); 
    denom1= denom1+ Ni * log(Ni/n);
end

denom2=0;
for j=1:size(CM,2) 
    
    Nj = sum(CM(:,j));
    denom2= denom2+ Nj * log(Nj/n);
end
    NMI = -2*numeratore/(denom1 + denom2)
end
