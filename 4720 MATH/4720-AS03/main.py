#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
import numpy as np


# In[114]:


db = pd.read_excel(
    'db_lec3.xls',
    sheet_name='db_lec3',
    header=0
)
db = pd.concat([pd.Series(1, index=df.index, name='00'), db], axis=1)
db.head()


# In[115]:


X = db.drop(columns='FINAL')
y = db.iloc[:, 4]


# In[116]:


for i in X.columns:
    X[i] = X[i]/np.max(X[i])
X.head()


# In[117]:


theta = np.array([0]*len(X.columns))


# In[118]:


m = len(db)


# In[119]:


def hypothesis(theta, X):
    return theta*X


# In[120]:


def computeCost(X, y, theta):
    y1 = hypothesis(theta, X)
    y1=np.sum(y1, axis=1)
    return sum(np.sqrt((y1-y)**2))/(2*m)


# In[121]:


def gradientDescent(X, y, theta, alpha, i):
    J = []  #cost function in each iterations
    k = 0
    while k < i:        
        y1 = hypothesis(theta, X)
        y1 = np.sum(y1, axis=1)
        for c in range(0, len(X.columns)):
            theta[c] = theta[c] - alpha*(sum((y1-y)*X.iloc[:,c])/len(X))
        j = computeCost(X, y, theta)
        J.append(j)
        k += 1
    return J, j, theta


# In[133]:


J, j, theta = gradientDescent(X, y, theta, 0.05, 100)


# In[134]:


y_hat = hypothesis(theta, X)
y_hat = np.sum(y_hat, axis=1)


# In[135]:


get_ipython().run_line_magic('matplotlib', 'inline')
import matplotlib.pyplot as plt
plt.figure()
plt.scatter(x=list(range(0, m)),y= y, color='blue')         
plt.scatter(x=list(range(0, m)), y=y_hat, color='black')
plt.show()


# In[136]:


x = db.values[:, 0:4]
plt.plot(x[:,1], y, "x")
plt.plot(x[:,1], x * theta, "r-")
plt.show()


# In[137]:


print("The parameters a, b, c, c are ", theta)


# In[138]:


print("minimum MSE", j)


# In[139]:


print("MSE: ", J)

