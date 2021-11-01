$$
\begin{aligned}
	L_n(\theta) &= \prod_{i=1}^{n}P(x|\theta) \rightarrow l(\theta) = \sum_{i=1}^n \log(x_i|\theta)\\
	L(\theta) &= (\frac{2\theta}{3})^2(\frac{\theta}{3})^3(\frac{2(1-\theta)}{3})^3(\frac{1-\theta}{3})^3 \\
	\log L(\theta) &= 2\log\frac{2\theta}{3} + 3\log\frac{\theta}{3}+3\log\frac{2(1-\theta)}{3}+3\log\frac{1-\theta}{3}\\
	\frac{\part l}{\part \theta}&=0 
\end{aligned}
$$

$$
\begin{aligned}
	f(X=x|\theta) &= \frac{\theta^ne^{-\theta}}{x!} \\
	L(x|\theta) &= \prod_{i=1}^n f(x_i|\theta) = \prod_{i=1}^n \frac{\theta^ne^{-\theta}}{x!} \\
	l(x|\theta) &= \log(\prod_{i=1}^n \frac{\theta^ne^{-\theta}}{x!}) \\
  l(x|\theta) &= \sum_{i=1}^n \log(\frac{\theta^ne^{-\theta}}{x!}) \\
    l(x|\theta) &= \sum_{i=1}^n [\log(\theta^{x}) + \log(e^{-\theta}) - \log(x!)] \\
    &= -n\theta + \log(\theta)\sum_{i=1}^n x_i - \sum_{i=1}^n\log(x_i!)


\end{aligned}
$$


$$
f(x|x_0, \theta) = \theta x_0^\theta x^{-\theta-1}
$$
