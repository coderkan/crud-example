create table [dbo].[VR_ISG](
	[id] [int] IDENTITY (1, 1) NOT NULL PRIMARY KEY,
	[name]VARCHAR(50),
	[date] DATE,
	[update_date] DATE,
	[sicil] VARCHAR(10),
	[flag][int]
)
