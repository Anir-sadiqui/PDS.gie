from time import time
import numpy as np
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
#                      Initialization
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
def T( s, a, s2 ):
   
  return P[s][a].get(s2, rs) #faire appel a P la matrice genere en code java
   
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.

#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
# Value iteration
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
def reward( s, a, s2 ):
   if a == Rp:
      l = 20 -  VMoy[s2]
      if l >=0:
         g= VMoy[s2] + SDep[s] -l 
      else : g= 20+ 2*l+ SDep[s]
   elif a== Rg:
      l = 35 -  VMoy[s2]
      if l >=0:
         g= VMoy[s2] + SDep[s] -l + SDep[s]
      else : g= 35+ 2*l+ SDep[s]
   elif a==G :
      l = SDep[s] -  VMoy[s2]
      if l >=0:
         g= 2 * VMoy[s2] 
      else : g=  2*l + 2*SDep[s]
   return g 
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
def valueIteration(EPSILON = 1e-15, maxIter=200, VERBOSE=False ):
   optPolicy = {}  ; V = newV = { s:0 for s in S }  
   for iteration in range(maxIter):
      oldV = V ; V = newV.copy()
      maxDiff = 0
      for s in S:
         maxV = -inf
         for a in actions[s]:
            Qsa = 0
            for s2 in S:
               Tsas2 = T(s, a, s2)
               if Tsas2 != rs :
                  Rsas2 = reward(s, a, s2)
                  Qsa += Tsas2 * (Rsas2 + GAMMA*oldV[s2])
               else : Tsas2 = 0 #appel algo ecrit en java de recherche du produit le plus proche 
            if Qsa > maxV: #Is this the best action so far? If so, keep it
               maxV = Qsa   ;  optPolicy[s] = a
         #Save the best of all actions for the state
         V[s] = maxV
         
         maxDiff = max(maxDiff, np.abs(oldV[s] - V[s]))
      if maxDiff < EPSILON: break

      if VERBOSE : print(iteration, "*"*50) ; printVals( V )      
   return V, optPolicy
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.      
GAMMA = 0.9  
S = { E , M , F}
actions = {Rp  ,Rg  ,G }   ; SDep = { E : 5, M : 8, F : 12} ; VMoy = {E : 30,  M : 17, F : 5}
inf = float("inf")
RandomPolicy = { s: np.random.choice( actions[s] ) for s in S}
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
t1 = time()  ;  V, optPolicy = valueIteration( )  ;  t2 = time()
print(t2 - t1)
#~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
print("~."*20); printVals(V) #print(*sorted(V.items(), key=lambda x:x[0]),sep='\t', end="\n"*7)         
print("~."*20); printPolicy(optPolicy)

